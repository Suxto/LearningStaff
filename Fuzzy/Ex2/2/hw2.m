clc; close all; clear;
tic;
%calc A, B, AC and BC
x=-4:0.001:9;
A = ((4 + x) / 7) .* (x >= -4 & x < 3) + ((9 - x) / 6) .* (x >= 3 & x <= 9);
B = exp(-abs(x - 2) / 8);
AC = 1 - A;
BC = 1 - B;
AandB = min(A, B);
AorB = max(A, B);
AandAC = min(A, AC);
AorAC = max(A, AC);
BandBC = min(B, BC);
BorBC = max(B, BC);

%plot A and B
figure;
plot(x, A, '-g', 'LineWidth', 1);
hold on;
plot(x, B, '-.k', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(max(A), max(B))]);
xlabel('x');
ylabel('Membership Function');
legend('A', 'B', 'location', 'best');
box off;

%plot AandB and AorB
figure;
plot(x, AandB, '-g', 'LineWidth', 1);
hold on;
plot(x, AorB, '-.k', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(max(AandB), max(AorB))]);
xlabel('x');
ylabel('Membership Function');
legend('A\capB', 'A\cupB', 'location', 'best');
box off;

%plot AandAC and AorAC
figure;
plot(x, AandAC, '-g', 'LineWidth', 1);
hold on;
plot(x, AorAC, '-.k', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(max(AandAC), max(AorAC))]);
xlabel('x');
ylabel('Membership Function');
legend('A\capA^C', 'A\cupA^C', 'location', 'best');
box off;

%plot BandBC and BorBC
figure;
plot(x, BandBC, '-g', 'LineWidth', 1);
hold on;
plot(x, BorBC, '-.k', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(max(BandBC), max(BorBC))]);
xlabel('x');
ylabel('Membership Function');
legend('B\capB^C', 'B\cupB^C', 'location', 'best');
box off;
