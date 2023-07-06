function [X, Y] = mix_fuzzy_op(A, B, C)
    X = syn(max(min(A, 1 - B), 1 - A), min(C', 1 - C));
    Y = syn(min(B, 1 - A), max(syn(A', B), C));
end
