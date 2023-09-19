library verilog;
use verilog.vl_types.all;
entity latch_8bit is
    port(
        inData          : in     vl_logic_vector(7 downto 0);
        outData         : out    vl_logic_vector(7 downto 0);
        LE              : in     vl_logic;
        nOE             : in     vl_logic
    );
end latch_8bit;
