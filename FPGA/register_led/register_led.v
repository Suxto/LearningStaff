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

module register_led (
    clk,
    nload,
    inData,
    outData,
    clk_out
);
    input clk;
    input nload;
    input [7:0] inData;
    output [7:0] outData;
    output clk_out;
    reg [7:0] outData;

    mod1HzClock Clock (
        .clk    (clk),
        .nrst   (nload),
        .clk_out(clk_out)
    );
    // assign clk_out = clk;

    always @(posedge clk_out or negedge nload) begin
        if (nload == 0) outData <= inData;
        else outData <= {outData[6:0], outData[7]};
    end

endmodule


