clc;clear;

A = input('Enter A  (e.g. [0 0.3 1 0.7 0.4]): ');

B = input('Enter B  (e.g. [0.8 0.3 0.5]): ');

AStar = input('Enter A*  (e.g. [0.2 0.3 0.8 0 0.6]): ');

fprintf('B* is\n');
disp(MZ_inference(A, B, AStar));
