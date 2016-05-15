clear all
clc

load iris_dataset

% Number of neurons
n = 4;

% Number of attributes and number of classifications
[n_attr, ~]  = size(irisInputs);
[n_class, ~] = size(irisTargets);

% Initialize neural network
net = feedforwardnet(n);

% Configure the neural network for this dataset
net = configure(net, irisInputs, irisTargets); %view(net);

fun = @(w) mse_test(w, net, irisInputs, irisTargets);

% Add 'Display' option to display result of iterations
ps_opts = psoptimset ( 'CompletePoll', 'off', 'Display', 'iter', 'MaxIter', 100); %, 'TimeLimit', 120 );

% There is n_attr attributes in dataset, and there are n neurons so there 
% are total of n_attr*n input weights (uniform weight)
initial_il_weights = ones(1, n_attr*n)/(n_attr*n);
% There are n bias values, one for each neuron (random)
initial_il_bias    = rand(1, n);
% There is n_class output, so there are total of n_class*n output weights 
% (uniform weight)
initial_ol_weights = ones(1, n_class*n)/(n_class*n);
% There are n_class bias values, one for each output neuron (random)
initial_ol_bias    = rand(1, n_class);
% starting values
starting_values = [initial_il_weights, initial_il_bias, ...
                   initial_ol_weights, initial_ol_bias];

[x, fval, flag, output] = patternsearch(fun, starting_values, [], [],[],[], -1e5, 1e5, ps_opts);
