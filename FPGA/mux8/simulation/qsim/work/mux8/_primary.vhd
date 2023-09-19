library verilog;
use verilog.vl_types.all;
entity mux8 is
    port(
        A               : in     vl_logic;
        B               : in     vl_logic;
        C               : in     vl_logic;
        D               : in     vl_logic;
        E               : in     vl_logic;
        F               : in     vl_logic;
        G               : in     vl_logic;
        H               : in     vl_logic;
        \select\        : in     vl_logic_vector(2 downto 0);
        andLogic        : out    vl_logic;
        orLogic         : out    vl_logic;
        notLogic        : out    vl_logic;
        nandLogic       : out    vl_logic;
        norLogic        : out    vl_logic;
        nxorLogic       : out    vl_logic;
        xorLogic        : out    vl_logic;
        muxout          : out    vl_logic
    );
end mux8;
