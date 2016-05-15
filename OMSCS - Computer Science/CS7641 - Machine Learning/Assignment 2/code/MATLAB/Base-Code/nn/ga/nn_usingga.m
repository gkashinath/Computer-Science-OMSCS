% INITIALIZE THE NEURAL NETWORK PROBLEM %

% inputs for the neural net
inputs = (1:10);
% targets for the neural net
targets = cos(inputs.^2);

% number of neurons
n = 2;

% create a neural network
net = feedforwardnet(n);

% configure the neural network for this dataset
net = configure(net, inputs, targets);

% create handle to the MSE_TEST function, that
% calculates MSE
h = @(x) mse_test(x, net, inputs, targets);

% Setting the Genetic Algorithms tolerance for
% minimum change in fitness function before
% terminating algorithm to 1e-8 and displaying
% each iteration's results.
ga_opts = gaoptimset('TolFun',1e-8,'Display','iter');

% PLEASE NOTE: For a feed-forward network
% with n neurons, 3n+1 quantities are required
% in the weights and biases column vector.
%
% a. n for the input weights
% b. n for the input biases
% c. n for the output weights
% d. 1 for the output bias

% running the genetic algorithm with desired options
[x_ga_opt, err_ga] = ga(h, 3*n+1, ga_opts);