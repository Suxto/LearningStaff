## Generated SDC file "register_led.out.sdc"

## Copyright (C) 1991-2013 Altera Corporation
## Your use of Altera Corporation's design tools, logic functions 
## and other software and tools, and its AMPP partner logic 
## functions, and any output files from any of the foregoing 
## (including device programming or simulation files), and any 
## associated documentation or information are expressly subject 
## to the terms and conditions of the Altera Program License 
## Subscription Agreement, Altera MegaCore Function License 
## Agreement, or other applicable license agreement, including, 
## without limitation, that your use is for the sole purpose of 
## programming logic devices manufactured by Altera and sold by 
## Altera or its authorized distributors.  Please refer to the 
## applicable agreement for further details.


## VENDOR  "Altera"
## PROGRAM "Quartus II"
## VERSION "Version 13.1.0 Build 162 10/23/2013 SJ Web Edition"

## DATE    "Tue Nov 29 12:47:53 2022"

##
## DEVICE  "EP4CE6E22C8"
##


#**************************************************************
# Time Information
#**************************************************************

set_time_format -unit ns -decimal_places 3



#**************************************************************
# Create Clock
#**************************************************************

create_clock -name {clk} -period 1.000 -waveform { 0.000 0.500 } [get_ports {clk}]
create_clock -name {mod1HzClock:Clock|clk_out} -period 1.000 -waveform { 0.000 0.500 } [get_registers {mod1HzClock:Clock|clk_out}]
create_clock -name {nload} -period 1.000 -waveform { 0.000 0.500 } [get_ports {nload}]


#**************************************************************
# Create Generated Clock
#**************************************************************



#**************************************************************
# Set Clock Latency
#**************************************************************



#**************************************************************
# Set Clock Uncertainty
#**************************************************************

set_clock_uncertainty -rise_from [get_clocks {nload}] -rise_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.030  
set_clock_uncertainty -rise_from [get_clocks {nload}] -fall_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.030  
set_clock_uncertainty -rise_from [get_clocks {nload}] -rise_to [get_clocks {clk}]  0.040  
set_clock_uncertainty -rise_from [get_clocks {nload}] -fall_to [get_clocks {clk}]  0.040  
set_clock_uncertainty -fall_from [get_clocks {nload}] -rise_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.030  
set_clock_uncertainty -fall_from [get_clocks {nload}] -fall_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.030  
set_clock_uncertainty -fall_from [get_clocks {nload}] -rise_to [get_clocks {clk}]  0.040  
set_clock_uncertainty -fall_from [get_clocks {nload}] -fall_to [get_clocks {clk}]  0.040  
set_clock_uncertainty -rise_from [get_clocks {mod1HzClock:Clock|clk_out}] -rise_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.020  
set_clock_uncertainty -rise_from [get_clocks {mod1HzClock:Clock|clk_out}] -fall_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.020  
set_clock_uncertainty -rise_from [get_clocks {mod1HzClock:Clock|clk_out}] -rise_to [get_clocks {clk}]  0.030  
set_clock_uncertainty -rise_from [get_clocks {mod1HzClock:Clock|clk_out}] -fall_to [get_clocks {clk}]  0.030  
set_clock_uncertainty -fall_from [get_clocks {mod1HzClock:Clock|clk_out}] -rise_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.020  
set_clock_uncertainty -fall_from [get_clocks {mod1HzClock:Clock|clk_out}] -fall_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.020  
set_clock_uncertainty -fall_from [get_clocks {mod1HzClock:Clock|clk_out}] -rise_to [get_clocks {clk}]  0.030  
set_clock_uncertainty -fall_from [get_clocks {mod1HzClock:Clock|clk_out}] -fall_to [get_clocks {clk}]  0.030  
set_clock_uncertainty -rise_from [get_clocks {clk}] -rise_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.030  
set_clock_uncertainty -rise_from [get_clocks {clk}] -fall_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.030  
set_clock_uncertainty -rise_from [get_clocks {clk}] -rise_to [get_clocks {clk}]  0.020  
set_clock_uncertainty -rise_from [get_clocks {clk}] -fall_to [get_clocks {clk}]  0.020  
set_clock_uncertainty -fall_from [get_clocks {clk}] -rise_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.030  
set_clock_uncertainty -fall_from [get_clocks {clk}] -fall_to [get_clocks {mod1HzClock:Clock|clk_out}]  0.030  
set_clock_uncertainty -fall_from [get_clocks {clk}] -rise_to [get_clocks {clk}]  0.020  
set_clock_uncertainty -fall_from [get_clocks {clk}] -fall_to [get_clocks {clk}]  0.020  


#**************************************************************
# Set Input Delay
#**************************************************************



#**************************************************************
# Set Output Delay
#**************************************************************



#**************************************************************
# Set Clock Groups
#**************************************************************



#**************************************************************
# Set False Path
#**************************************************************



#**************************************************************
# Set Multicycle Path
#**************************************************************



#**************************************************************
# Set Maximum Delay
#**************************************************************



#**************************************************************
# Set Minimum Delay
#**************************************************************



#**************************************************************
# Set Input Transition
#**************************************************************
