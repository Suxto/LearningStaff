clc;close all;clear;
tic;
x=-1:1:3;
A=0.16*x.^2-0.48*x+0.36;
figure;
stem(x,A);
xmin=min(x);
xmax=max(x);
ymax=max(A);
axis([xmin-0.2,xmax+0.5,0,1.05*ymax]);
xlabel('x');
ylabel('Membership Function');
box off;
toc;