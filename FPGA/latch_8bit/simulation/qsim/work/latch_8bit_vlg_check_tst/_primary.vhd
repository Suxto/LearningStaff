library verilog;
use verilog.vl_types.all;
entity latch_8bit_vlg_check_tst is
    port(
        outData         : in     vl_logic_vector(7 downto 0);
        sampler_rx      : in     vl_logic
    );
end latch_8bit_vlg_check_tst;
