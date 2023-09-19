library verilog;
use verilog.vl_types.all;
entity TestRam is
    port(
        bclk            : in     vl_logic;
        in244           : in     vl_logic_vector(7 downto 0);
        ABus            : in     vl_logic_vector(7 downto 0);
        DBus            : out    vl_logic_vector(7 downto 0);
        bnRD            : in     vl_logic;
        bnWR            : in     vl_logic
    );
end TestRam;
