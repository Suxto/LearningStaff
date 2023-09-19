module A74LS181 (
    A,
    B,
    S,
    CN,
    M,
    F,
    CN4
);
    input [3:0] A, B;
    input [3:0] S;
    input M, CN;
    output CN4;
    output [3:0] F;
    reg [3:0] F;
    reg CN4;
    reg [3:0] OA, OB;  

    always @(S) begin
        OA = ~A;
        OB = ~B;
        case (S)
            4'b0000: begin
                if (M) F = OA;
                else begin
                    if (CN) {CN4, F} = A;
                    else {CN4, F} = A + 1;
                end
            end
            4'b0001: begin
                if (M) F = ~(A | B);
                else begin
                    if (CN) {CN4, F} = A | B;
                    else {CN4, F} = (A | B) + 1;
                end
            end
            4'b0010: begin
                if (M) F = OA & B;
                else begin
                    if (CN) {CN4, F} = A | OB;
                    else {CN4, F} = (A | OB) + 1;
                end
            end
            4'b0011: begin
                if (M) F = 0;
                else begin
                    if (CN) {CN4, F} = -1;
                    else {CN4, F} = 0;
                end
            end
            4'b0100: begin
                if (M) F = ~(A & B);
                else begin
                    if (CN) {CN4, F} = A + (A & OB);
                    else {CN4, F} = A + (A & OB) + 1;
                end
            end
            4'b0101: begin
                if (M) F = OB;
                else begin
                    if (CN) {CN4, F} = (A | B) + (A & OB);
                    else {CN4, F} = (A | B) + (A & OB) + 1;
                end
            end
            4'b0110: begin
                if (M) F = A ^ B;
                else begin
                    if (CN) {CN4, F} = A - B - 1;
                    else {CN4, F} = A - B;
                end
            end
            4'b0111: begin
                if (M) F = A & OB;
                else begin
                    if (CN) {CN4, F} = (A & OB) - 1;
                    else {CN4, F} = A & OB;
                end
            end
            4'b1000: begin
                if (M) F = OA | B;
                else begin
                    if (CN) {CN4, F} = A + (A & B);
                    else {CN4, F} = A + (A & B) + 1;
                end
            end
            4'b1001: begin
                if (M) F = ~(A ^ B);
                else begin
                    if (CN) {CN4, F} = A + B;
                    else {CN4, F} = A + B + 1;
                end
            end
            4'b1010: begin
                if (M) F = B;
                else begin
                    if (CN) {CN4, F} = (A | OB) + (A & B);
                    else {CN4, F} = (A | OB) + (A & B) + 1;
                end
            end
            4'b1011: begin
                if (M) F = B & A;
                else begin
                    if (CN) {CN4, F} = (A & B) - 1;
                    else {CN4, F} = (A & B);
                end
            end
            4'b1100: begin
                if (M) F = 1;
                else begin
                    if (CN) {CN4, F} = A + A;
                    else {CN4, F} = A + A + 1;
                end
            end
            4'b1101: begin
                if (M) F = A | OB;
                else begin
                    if (CN) {CN4, F} = (A | B) + A;
                    else {CN4, F} = (A | B) + A + 1;
                end
            end
            4'b1110: begin
                if (M) F = A | B;
                else begin
                    if (CN) {CN4, F} = (A | OB) + A;
                    else {CN4, F} = (A | OB) + A + 1;
                end
            end
            4'b1111: begin
                if (M) F = A;
                else begin
                    if (CN) {CN4, F} = A - 1;
                    else {CN4, F} = A;
                end
            end
            default: F = 4'bxxxx;
        endcase
    end
endmodule


module StaticLED (
    inData,
    segData
);
    input [3:0] inData;
    output [7:0] segData;

    reg [7:0] segData;

    always @(inData) begin
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
            4'b1001: segData <= 8'b01100111;  //9
            4'b1010: segData <= 8'b00111111;  //0
            4'b1011: segData <= 8'b00000110;
            4'b1100: segData <= 8'b01011011;
            4'b1101: segData <= 8'b01001111;
            4'b1110: segData <= 8'b01100110;
            4'b1111: segData <= 8'b01101101;
            default: segData <= 8'bx;
        endcase
    end

endmodule


module Calc (
    A,
    B,
    S,
    CN,
    M,
    F,
    CN4,
    segOut,
    digOut
);
    input [3:0] A, B;
    input [3:0] S;
    input M, CN;
    output CN4;
    output [3:0] F;
    wire [3:0] F;

    output [7:0] segOut;
    wire [7:0] segOut;
    output [7:0] digOut;
    wire [7:0] digOut;

    assign digOut = 8'b11111110;

    A74LS181 ALU (
        .A  (A),
        .B  (B),
        .S  (S),
        .CN (CN),
        .M  (M),
        .F  (F),
        .CN4(CN4)
    );

    StaticLED LED (
        .inData (F),
        .segData(segOut)
    );

endmodule
