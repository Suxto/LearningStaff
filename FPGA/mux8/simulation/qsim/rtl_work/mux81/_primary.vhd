library verilog;
use verilog.vl_types.all;
entity mux81 is
    port(
        A               : in     vl_logic;
        B               : in     vl_logic;
        C               : in     vl_logic;
        D               : in     vl_logic;
        E               : in     vl_logic;
        F               : in     vl_logic;
        G               : in     vl_logic;
        H               : in     vl_logic;
        sel             : in     vl_logic_vector(2 downto 0);
        \out\           : out    vl_logic
    );
end mux81;
