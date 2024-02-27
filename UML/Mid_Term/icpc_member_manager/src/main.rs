pub mod command_handler;
pub mod database_access;
pub mod entities;

use command_handler::command_handler_fun;
use database_access::{get_squad_name, init_db};
use rusqlite::{Connection, OpenFlags};
use rustyline::error::ReadlineError;
use rustyline::{DefaultEditor, Result};
use std::ops::Add;
use std::path::Path;

static DB_NAME: &str = "system_db";
static DB_ERR_STATEMENT: &str = "Access to database error!\nDelete system_db reopen this and try
again";

fn main() -> Result<()> {
    let mut rl = DefaultEditor::new()?;
    let db_path = Path::new(DB_NAME);
    if !db_path.exists() {
        println!("未检测到数据库文件，开始初始化系统");
        loop {
            if let Ok(name) = rl.readline("集训队名称：") {
                let conn = Connection::open_with_flags(
                    db_path,
                    OpenFlags::SQLITE_OPEN_READ_WRITE | OpenFlags::SQLITE_OPEN_CREATE,
                )
                .unwrap();
                match init_db(name, &conn) {
                    Ok(()) => (),
                    Err(err) => println!("Error:{err}"),
                }
                let _ = conn.close();
                println!(
                    "初始化成功！\n数据将储存在工作目录下的 system_db 中，请勿删除或移动该文件！"
                );
                break;
            } else {
                println!("请输入正确的名称！")
            }
        }
    }
    let conn = Connection::open_with_flags(db_path, OpenFlags::SQLITE_OPEN_READ_WRITE).unwrap();
    let mut prompt = String::new();
    match get_squad_name(&conn) {
        Ok(name) => prompt = name.add(">> "),
        Err(err) => println!("{DB_ERR_STATEMENT}\nInfo: {err}"),
    }
    let prompt = prompt;
    loop {
        let readline = rl.readline(&prompt);
        match readline {
            Ok(line) => {
                let _ = rl.add_history_entry(line.as_str());
                command_handler_fun(&line, &mut rl, &conn);
            }
            Err(ReadlineError::Interrupted) => {
                println!("CTRL-C pressed, exiting...");
                break;
            }
            Err(ReadlineError::Eof) => {
                println!("CTRL-D pressed, exiting...");
                break;
            }
            Err(err) => {
                println!("Error: {:?}", err);
                break;
            }
        }
    }
    let _ = conn.close();
    Ok(())
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::entities::Member;
    use database_access::init_db;
    use rusqlite::{Connection, OpenFlags};
    use std::path::Path;

    #[ignore]
    #[test]
    fn db_up() {
        let db_path = Path::new("system_db");
        if !db_path.exists() {
            println!("初始化数据库");
            let conn = Connection::open_with_flags(
                db_path,
                OpenFlags::SQLITE_OPEN_READ_WRITE | OpenFlags::SQLITE_OPEN_CREATE,
            )
            .unwrap();
            let name = "test";
            match init_db(name.to_string(), &conn) {
                Ok(()) => (),
                Err(err) => println!("Error:{err}"),
            }
            let _ = conn.close();
            println!("建表完成");
        }
        let conn = Connection::open_with_flags(
            db_path,
            OpenFlags::SQLITE_OPEN_READ_WRITE | OpenFlags::SQLITE_OPEN_CREATE,
        )
        .unwrap();

        let _ = database_access::add_member(
            &conn,
            &Member::new(&"2021117405".to_string(), &"孙潇桐".to_string()),
        );
        let _ = database_access::add_member(
            &conn,
            &Member::new(&"2021117406".to_string(), &"潇孙桐".to_string()),
        );
        let _ = database_access::add_member(
            &conn,
            &Member::new(&"2021117407".to_string(), &"桐孙潇".to_string()),
        );
        let _ = database_access::add_member(
            &conn,
            &Member::new(&"2021117408".to_string(), &"孙桐潇".to_string()),
        );
        println!("已添加测试数据");
    }
}
