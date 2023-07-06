function BStar = MZ_inference(A, B, AStar)
    F = min(A, AStar);
    lambda = max(F);
    BStar = min(ones(size(B)) .* lambda, B);
end
