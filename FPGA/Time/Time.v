module Time (
    nRst,
    CLK,
    nSTART,
    nSTOP,
    T1,
    T2,
    T3
);
    input nRst, CLK, nSTART, nSTOP;
    output T1, T2, T3;
    reg T1, T2, T3;
    wire clk_out;


    // mod1HzClock clock (
    //     .clk    (CLK),
    //     .clk_out(clk_out),
    //     .nrst   (nRst)
    // );

    assign clk_out = CLK;

    always @(posedge clk_out or negedge nRst) begin
        if (nRst == 0) begin
            T1 <= 0;
            T2 <= 0;
            T3 <= 0;
        end else if (nSTART == 0 && nSTOP && T1 == 0 && T2 == 0 && T3 == 0) begin
            T1 <= 1;
            T2 <= 0;
            T3 <= 0;
        end else if (nSTOP == 0 && T3 == 1) begin
            T1 <= 0;
            T2 <= 0;
            T3 <= 0;
        end else if (nSTOP == 1) begin
            if (T1) begin
                T1 <= 0;
                T2 <= 1;
                T3 <= 0;
            end else if (T2) begin
                T1 <= 0;
                T2 <= 0;
                T3 <= 1;
            end else begin
                T1 <= 1;
                T2 <= 0;
                T3 <= 0;
            end
        end
    end
endmodule



module mod1HzClock (
    clk,
    nrst,
    clk_out
);
    input clk;
    input nrst;
    output clk_out;

    reg clk_out;
    reg [25:0] counter;


    always @(posedge clk or negedge nrst) begin
        if (nrst == 0) begin
            clk_out <= 0;
            counter <= 0;
        end else if (counter != 24999999) counter <= counter + 1;
        else begin
            clk_out <= !clk_out;
            counter <= 0;
        end
    end
endmodule