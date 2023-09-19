library verilog;
use verilog.vl_types.all;
entity mux8_vlg_sample_tst is
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
        sampler_tx      : out    vl_logic
    );
end mux8_vlg_sample_tst;
