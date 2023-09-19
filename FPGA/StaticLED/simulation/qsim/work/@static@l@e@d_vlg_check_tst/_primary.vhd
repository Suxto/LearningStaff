library verilog;
use verilog.vl_types.all;
entity StaticLED_vlg_check_tst is
    port(
        counter         : in     vl_logic_vector(3 downto 0);
        digOut          : in     vl_logic_vector(7 downto 0);
        segOut          : in     vl_logic_vector(7 downto 0);
        sampler_rx      : in     vl_logic
    );
end StaticLED_vlg_check_tst;
