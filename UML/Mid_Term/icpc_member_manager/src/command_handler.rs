use std::process::exit;

use crate::entities::{Coach, Member, Team};

use super::database_access::*;
use prettytable::{row, Table};
use rusqlite::Connection;
use rustyline::error::ReadlineError;
use rustyline::history::DefaultHistory;
use rustyline::Editor;

pub fn command_handler_fun(line: &String, rl: &mut Editor<(), DefaultHistory>, conn: &Connection) {
    let command_seq: Vec<String> = line
        .trim()
        .split_ascii_whitespace()
        .map(|x| x.to_string())
        .collect();
    if command_seq.is_empty() {
        return;
    }
    let command = command_seq.first().unwrap();
    if command.starts_with("202") && command.len() == 10 {
        match query_member(conn, &command) {
            Ok(member) => {
                if let Err(e) = log_in_or_out(conn, &member) {
                    panic!("Err: {e}");
                }
            }
            Err(_) => {
                if let Ok(res) = rl.readline("未找到该学号，是否创建成员[Y,n]") {
                    if res.is_empty() || res.to_ascii_lowercase() == "y" {
                        let name = rl.readline("请输入姓名：").unwrap();
                        let member = Member::new(&command, &name);
                        if let Err(err) = add_member(conn, &member) {
                            panic!("Database error, Cause: {err}");
                        }
                        println!("成员创建成功，再次输入学号即可登录");
                    } else if res.to_ascii_lowercase() == "n" {
                        return;
                    } else {
                        println!("请按提示输入");
                    }
                } else {
                    println!("输入错误");
                    return;
                }
            }
        }
    } else if command == "list" {
        list_handler(&command_seq, conn);
    } else if command == "team" {
        team_handler(&command_seq, conn, rl);
    } else if command == "coach" {
        coach_handler(&command_seq, conn, rl);
    } else if command == "help" {
        help_handler();
    } else if command == "exit" {
        exit(0);
    } else {
        println!("Invalid command. Try input help to get help.");
    }
}

fn list_handler(cmd_seq: &Vec<String>, conn: &Connection) {
    if cmd_seq.len() == 1 || cmd_seq[1] == "help" {
        println!("Usage of command list:");
        println!("list help         show this help");
        println!("list login        list the member who haven't logout");
        println!("list members      list members in the database");
        println!("list teams        list teams in the database");
        println!("list coaches      list coaches in the database");
    } else if cmd_seq.len() == 2 {
        if cmd_seq[1] == "login" {
            let mem_vec = get_members(conn, true).unwrap();
            let mut table = Table::new();
            table.add_row(row!["ID", "Name", "Login Time"]);
            mem_vec.iter().for_each(|x| {
                table.add_row(x.to_row());
            });
            table.printstd();
        } else if cmd_seq[1] == "members" {
            let mem_vec = get_members(conn, false).unwrap();
            let mut table = Table::new();
            table.add_row(row!["ID", "Name", "Join Time"]);
            mem_vec.iter().for_each(|x| {
                table.add_row(x.to_row());
            });
            table.printstd();
        } else if cmd_seq[1] == "teams" {
            let mem_vec = get_teams(conn).unwrap();
            let mut table = Table::new();
            table.add_row(row![
                "Team ID",
                "Team Name",
                "Member_1 ID",
                "Member_2 ID",
                "Member_3 ID",
                "Coach ID",
            ]);
            mem_vec.iter().for_each(|x| {
                table.add_row(x.to_row());
            });
            table.printstd();
        } else if cmd_seq[1] == "coaches" {
            let mem_vec = get_coaches(conn).unwrap();
            let mut table = Table::new();
            table.add_row(row!["Coach ID", "Coach Name",]);
            mem_vec.iter().for_each(|x| {
                table.add_row(x.to_row());
            });
            table.printstd();
        } else {
            println!("Invalid command. Try input 'list help' to get help.");
        }
    } else {
        println!("Too many arguments, try 'list help' to get help.");
    }
}

fn help_handler() {
    println!("Welcom to icpc member manage system.");
    println!("About login:");
    println!("Type your 10-digit student id to login or logout");
    println!("System would ask your name for the first login.");
    println!("Once your name is saved, you can login by type in your id.");
    println!("If you have login, type your id again to logout.");
    println!("Other commands:");
    println!("list      list infomations, input 'list help' to learn more");
    println!("export    export weekly report, input 'export help' to learn more");
    println!("team      manage team, input 'team help' to learn more");
    println!("coach     manage coach, input 'coach help' to learn more");
    println!("exit      exit the system");
}

fn coach_handler(cmd_seq: &Vec<String>, conn: &Connection, rl: &mut Editor<(), DefaultHistory>) {
    if cmd_seq.len() == 1 || cmd_seq[1] == "help" {
        println!("Usage of command coach:");
        println!("coach help                 show this help");
        println!("coach add                  add a new coach");
        // println!("list remove [team_id]     remove a team with team_id");
        println!("coach edit [coach_id]      edit a coach with coach_id");
    } else if cmd_seq[1] == "add" {
        let coach_name = match rl.readline("请输入教练名称：") {
            Ok(t) => t,
            Err(_) => {
                println!("输入错误，请重试");
                return;
            }
        };
        if let Err(e) = add_coach(conn, &coach_name) {
            panic!("Database error, cause: {e}");
        }
    } else if cmd_seq[1] == "edit" {
        if cmd_seq.len() < 3 {
            println!("Need more arguments");
            return;
        }
        let coach_id: i32 = match cmd_seq[2].parse() {
            Ok(id) => id,
            Err(_) => {
                println!("输入错误，请重试");
                return;
            }
        };
        let coach_name = match rl.readline("请输入教练名称：") {
            Ok(t) => t,
            Err(_) => {
                println!("输入错误，请重试");
                return;
            }
        };
        // println!("{coach_id}, {coach_name}");
        let coach = Coach::new(coach_id, coach_name);
        if let Err(e) = update_coach(conn, &coach) {
            panic!("Database error, cause: {e}");
        }
    }
}

fn team_handler(cmd_seq: &Vec<String>, conn: &Connection, rl: &mut Editor<(), DefaultHistory>) {
    if cmd_seq.len() == 1 || cmd_seq[1] == "help" {
        println!("Usage of command team:");
        println!("team help                 show this help");
        println!("team add                  add a new team");
        // println!("list remove [team_id]     remove a team with team_id");
        println!("team edit [team_id]       edit a team with team_id");
    } else if cmd_seq[1] == "add" {
        let team = match team_info_gather(rl) {
            Ok(t) => t,
            Err(_) => {
                println!("输入错误，请重试");
                return;
            }
        };
        if let Err(e) = add_team(conn, &team) {
            panic!("Database error, cause: {e}");
        }
    } else if cmd_seq[1] == "edit" {
        if cmd_seq.len() < 3 {
            println!("Need more arguments");
            return;
        }
        let team_id: i32 = match cmd_seq[2].parse() {
            Ok(id) => id,
            Err(_) => {
                println!("输入错误，请重试");
                return;
            }
        };
        let mut team = match team_info_gather(rl) {
            Ok(t) => t,
            Err(_) => {
                println!("输入错误，请重试");
                return;
            }
        };
        team.set_id(team_id);
        if let Err(e) = update_team(conn, &team) {
            panic!("Database error, cause: {e}");
        }
    }
}

fn team_info_gather(rl: &mut Editor<(), DefaultHistory>) -> Result<Team, ReadlineError> {
    println!("Please input the team info: ");
    let name = rl.readline("团队名称：")?;
    let member_1_id = rl.readline("队员一学号：")?;
    let member_2_id = rl.readline("队员二学号：")?;
    let member_3_id = rl.readline("队员三学号：")?;
    let coach_id = rl.readline("教练id：")?;
    let coach_id: i32 = match coach_id.parse() {
        Ok(x) => x,
        Err(_) => return Err(ReadlineError::Eof),
    };
    Ok(Team::new(
        -1,
        name,
        member_1_id,
        member_2_id,
        member_3_id,
        coach_id,
    ))
}
