library verilog;
use verilog.vl_types.all;
entity trigger is
    port(
        nCLR            : in     vl_logic;
        CLK             : in     vl_logic;
        D               : in     vl_logic_vector(7 downto 0);
        Q               : out    vl_logic_vector(7 downto 0)
    );
end trigger;
