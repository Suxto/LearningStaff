clc;clear;close all;
tic;
x=-1:1:6;
y=abs(0.05*x.^3-0.35*x.^2+0.2*x+0.6);
u=x(y~=0);
v=x(abs(y-1)<1e-10);
toc;
