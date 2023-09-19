library verilog;
use verilog.vl_types.all;
entity StaticLED is
    port(
        clk             : in     vl_logic;
        nRst            : in     vl_logic;
        segOut          : out    vl_logic_vector(7 downto 0);
        digOut          : out    vl_logic_vector(7 downto 0);
        counter         : out    vl_logic_vector(3 downto 0)
    );
end StaticLED;
