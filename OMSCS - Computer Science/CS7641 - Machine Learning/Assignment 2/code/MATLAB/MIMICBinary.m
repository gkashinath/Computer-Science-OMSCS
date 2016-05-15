function [MinCost] = MIMICBinary(ProblemFunction, DisplayFlag, RandSeed, GenLimit, UpdatePct, ...
    FirstOrder, RandSecond, EntropyFlag)

% Mutual information maximization for input clustering (MIMIC) for minimizing a function on a binary domain.
% INPUTS: ProblemFunction = the handle of the function that returns 
%                           the handles of the initialization and cost functions.
%         DisplayFlag = true or false, whether or not to display and plot results.
%         RandSeed = random number seed for reproducibility of results
%         GenLimit = generation count
%         UpdatePct = percent of best solutions used to update probability vector
%         FirstOrder = true or false, whether or not to use only first-order probabilities
%         RandSecond = true or false, whether or not to use random bit pairs for second-order probabilities
%         EntropyFlag = true or false, whether or not to use minimum entropy instead of maximum information
%                       If EntropyFlag = true, then we have the MIMIC algorithm.
%                       If EntropyFlag = false, then we have the COMIT algorithm.
% OUTPUT: MinCost = array of best solutions, one element for each generation

if ~exist('ProblemFunction', 'var') || isempty(ProblemFunction)
    ProblemFunction = @AckleyDisc;
end
if ~exist('DisplayFlag', 'var') || isempty(DisplayFlag)
    DisplayFlag = true;
end
if ~exist('RandSeed', 'var') || isempty(RandSeed)
    RandSeed = [];
end
if ~exist('GenLimit', 'var') || isempty(GenLimit)
    GenLimit = 20;
end
OPTIONS.Maxgen = GenLimit;
if ~exist('UpdatePct', 'var') || isempty(UpdatePct)
    UpdatePct = 50;
end
UpdatePct = UpdatePct / 100;
if ~exist('FirstOrder', 'var') || isempty(FirstOrder)
    FirstOrder = false;
end
if ~exist('RandSecond', 'var') || isempty(RandSecond)
    RandSecond = false;
end
if ~exist('EntropyFlag', 'var') || isempty(EntropyFlag)
    EntropyFlag = true;
end

OPTIONS.Dim = 10;
OPTIONS.popsize = 100;
OPTIONS.pmutate = 0;
OPTIONS.Gray = false;
OPTIONS.clearDups = false;

[OPTIONS, MinCost, AvgCost, Population, MinConstrViol, AvgConstrViol] = ...
    Init(DisplayFlag, ProblemFunction, OPTIONS, RandSeed);
kArray = zeros(1, OPTIONS.numVar);
% JointProbs(i, j, k, m) is the probability that bit i = (k-1) and bit j = (m-1)
% JointProbs(i, i, k, k) is the probability that bit i = (k-1)
JointProbs = zeros(OPTIONS.numVar, OPTIONS.numVar, 2, 2);
% CondProb(i, j, k, m) is the probability that bit i = (k-1) if bit j = (m-1)
CondProbs = zeros(OPTIONS.numVar, OPTIONS.numVar, 2, 2);
MutualInfo = zeros(OPTIONS.numVar, OPTIONS.numVar); % mutual information between bits
% Entropy(i, j) is the conditional entropy of bit i given bit j
% Entropy(i, i) is the entropy of bit i
Entropy = zeros(OPTIONS.numVar, OPTIONS.numVar);
UpdateCount = round(UpdatePct * OPTIONS.popsize);

% Begin the evolution loop
for GenIndex = 1 : OPTIONS.Maxgen
    ElitePop = Population(1 : OPTIONS.Keep);
    % Find all the joint probabilities
    Chroms = reshape([Population(1:UpdateCount).chrom], OPTIONS.numVar, UpdateCount)';
    MaxOneOrZeroProb = 0;
    JointProbs(:, :, :, :) = 0;
    for i = 1 : OPTIONS.numVar
        for ind = 1 : UpdateCount
            iIndex = Chroms(ind, i) + 1;
            JointProbs(i, i, iIndex, iIndex) = JointProbs(i, i, iIndex, iIndex) + 1;
        end
        for j = i+1 : OPTIONS.numVar
            for ind = 1 : UpdateCount
                iIndex = Chroms(ind, i) + 1;
                jIndex = Chroms(ind, j) + 1;
                JointProbs(i, j, iIndex, jIndex) = JointProbs(i, j, iIndex, jIndex) + 1;
            end
            JointProbs(j, i, 1, 1) = JointProbs(i, j, 1, 1);
            JointProbs(j, i, 2, 2) = JointProbs(i, j, 2, 2);
            JointProbs(j, i, 1, 2) = JointProbs(i, j, 2, 1);
            JointProbs(j, i, 2, 1) = JointProbs(i, j, 1, 2);
        end
        if JointProbs(i, i, 1, 1) > MaxOneOrZeroProb
            MaxOneOrZeroProb = JointProbs(i, i, 1, 1);
            kArray(1) = i;
        end
        if JointProbs(i, i, 2, 2) > MaxOneOrZeroProb
            MaxOneOrZeroProb = JointProbs(i, i, 2, 2);
            kArray(1) = i;
        end
    end
    JointProbs = JointProbs / UpdateCount;
    JointProbs = min( max( JointProbs, 0.01), 0.99); % Make sure none of the probabilities are 0 or 1
    % Find the mutual information between each pair of bits
    MutualInfo(:, :) = 0;
    for i = 1 : OPTIONS.numVar
        for j = i+1 : OPTIONS.numVar
            for iBit = 1 : 2
                for jBit = 1 : 2
                    MutualInfo(i, j) = MutualInfo(i, j) + JointProbs(i, j, iBit, jBit) * log(JointProbs(i, j, iBit, jBit) / JointProbs(i, i, iBit, iBit) / JointProbs(j, j, jBit, jBit));
                end
            end
            MutualInfo(j, i) = MutualInfo(i, j);
        end
    end
    % Calculate conditional probabilities
    CondProbs(:, :, :, :) = 0;
    for i = 1 : OPTIONS.numVar
        for j = 1 : OPTIONS.numVar
            CondProbs(i, j, :, 1) = JointProbs(i, j, :, 1) / JointProbs(j, j, 1, 1);
            CondProbs(i, j, :, 2) = JointProbs(i, j, :, 2) / JointProbs(j, j, 2, 2);
        end
    end
    % Calculate entropy terms
    for i = 1 : OPTIONS.numVar
        for j = 1 : OPTIONS.numVar
            if i == j
                Entropy(i, i) = -JointProbs(i, i, 1, 1) * log(JointProbs(i, i, 1, 1)) - ...
                    JointProbs(i, i, 2, 2) * log(JointProbs(i, i, 2, 2));
            else    
                entropy1 = -CondProbs(i, j, 1, 1) * log(CondProbs(i, j, 1, 1)) - ...
                    CondProbs(i, j, 2, 1) * log(CondProbs(i, j, 2, 1));
                entropy2 = -CondProbs(i, j, 1, 2) * log(CondProbs(i, j, 1, 2)) - ...
                    CondProbs(i, j, 2, 2) * log(CondProbs(i, j, 2, 2));
                Entropy(i, j) = -entropy1 * JointProbs(j, j, 1, 1) - ...
                    entropy2 * JointProbs(j, j, 2, 2);
            end
        end
    end
    % Calculate the best permutation using a greedy algorithm
    if RandSecond
        kArray = randperm(OPTIONS.numVar);
    else
        for m = 2 : OPTIONS.numVar
            if EntropyFlag
                % Find the bit that has the least mutual entropy with bit # kArray(m-1)
                EntropyVec = Entropy(:, kArray(m-1));
                EntropyVec(kArray(1:m-1)) = inf;
                [~, kArray(m)] = min(EntropyVec);
            else
                % Find the bit that has the most mututal information with bit # kArray(m-1)
                MutualInfoVec = MutualInfo(kArray(m-1), :);
                MutualInfoVec(kArray(1:m-1)) = -inf;
                [~, kArray(m)] = max(MutualInfoVec);
            end            
        end
    end    
    % Generate a population based on the probabilities
    for popindex = OPTIONS.Keep+1 : OPTIONS.popsize
        Population(popindex).chrom(kArray(1)) = (rand < JointProbs(kArray(1), kArray(1), 2, 2));
        for m = 2 : OPTIONS.numVar
            PrevIndx = Population(popindex).chrom(kArray(m-1)) + 1;
            if FirstOrder
                Population(popindex).chrom(kArray(m)) = (rand < JointProbs(kArray(m), kArray(m), 2, 2));
            else
                Population(popindex).chrom(kArray(m)) = (rand < CondProbs(kArray(m), kArray(m-1), 2, PrevIndx));
            end            
        end
    end
    % Mutation (don't mutate the elites)
    for i = OPTIONS.Keep+1 : OPTIONS.popsize
        for k = 1 : OPTIONS.numVar
            if rand < OPTIONS.pmutate
                Population(i).chrom(k) = ~Population(i).chrom(k);
            end
        end
    end
    % Calculate cost
    Population = OPTIONS.CostFunction(Population, OPTIONS);
    % Sort from best to worst
    Population = PopSort(Population);
    % Replace the worst individuals with the best from the previous generation
    for i = 1 : OPTIONS.Keep
        Population(OPTIONS.popsize-i+1) = ElitePop(i);
    end
    Population = PopSort(Population);
    [MinCost, AvgCost, MinConstrViol, AvgConstrViol] = ComputeCostAndConstrViol(Population, ...
        MinCost, AvgCost, MinConstrViol, AvgConstrViol, GenIndex, DisplayFlag);
end
Conclude(DisplayFlag, OPTIONS, Population, MinCost, AvgCost, MinConstrViol, AvgConstrViol);
return