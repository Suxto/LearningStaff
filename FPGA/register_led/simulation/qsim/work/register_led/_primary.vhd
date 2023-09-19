library verilog;
use verilog.vl_types.all;
entity register_led is
    port(
        clk             : in     vl_logic;
        nload           : in     vl_logic;
        inData          : in     vl_logic_vector(7 downto 0);
        outData         : out    vl_logic_vector(7 downto 0);
        clk_out         : out    vl_logic
    );
end register_led;
