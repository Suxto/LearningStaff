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


module encoder (
    inData,
    segData
);
    input [3:0] inData;
    output [7:0] segData;

    reg [7:0] segData;

    always @(inData)
        case (inData)
            4'b0000: segData <= 8'b00111111;
            4'b0001: segData <= 8'b00000110;
            4'b0010: segData <= 8'b01011011;
            4'b0011: segData <= 8'b01001111;
            4'b0100: segData <= 8'b01100110;
            4'b0101: segData <= 8'b01101101;
            4'b0110: segData <= 8'b01111101;
            4'b0111: segData <= 8'b00000111;
            4'b1000: segData <= 8'b01111111;
            4'b1001: segData <= 8'b01100111;
            default: segData <= 8'bx;
        endcase

endmodule
module StaticLED (
    clk,
    nRst,
    segOut,
    digOut,
    counter
);
    input clk, nRst;
    output [7:0] segOut;
    output [7:0] digOut;
    output [3:0] counter;
    wire [7:0] digOut;
    wire [7:0] segOut;
    reg [3:0] counter;

    wire clk_out;

    assign digOut = 8'b11111110;  //0号数码管显示


    mod1HzClock Clock (
        .clk    (clk),
        .nrst   (nRst),
        .clk_out(clk_out)
    );
    // assign clk_out = clk;

    always @(posedge clk_out or negedge nRst) begin
        if (!nRst) counter <= 0;
        else if (counter != 4'b1001) counter <= counter + 1;
        else counter <= 0;
    end
    encoder coder (
        .inData (counter),
        .segData(segOut)
    );

endmodule
