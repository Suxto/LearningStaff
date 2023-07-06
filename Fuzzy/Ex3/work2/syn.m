function C = syn(A, B)
    p = size(A); q = size(B); s = max(max(max(A)), max(max(B)));
    t = min(min(min(A)), min(min(B)));

    if p(2) ~= q(1) || s > 1 || t < 0
        fprintf('Error!\n');
    else
        C = zeros(p(1), q(2));

        for i = 1:p(1)

            for j = 1:q(2)
                m = A(i, :); n = B(:, j); a = zeros(1, p(2));

                for r = 1:p(2)
                    a(r) = min(m(r), n(r));
                end

                C(i, j) = max(a);
            end

        end

    end
