library verilog;
use verilog.vl_types.all;
entity Calc is
    port(
        A               : in     vl_logic_vector(3 downto 0);
        B               : in     vl_logic_vector(3 downto 0);
        S               : in     vl_logic_vector(3 downto 0);
        CN              : in     vl_logic;
        M               : in     vl_logic;
        F               : out    vl_logic_vector(3 downto 0);
        CN4             : out    vl_logic;
        segOut          : out    vl_logic_vector(7 downto 0);
        digOut          : out    vl_logic_vector(7 downto 0)
    );
end Calc;
