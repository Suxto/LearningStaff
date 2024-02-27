use std::borrow::Cow;

use chrono::NaiveDateTime;
use prettytable::{row, Row};

#[derive(Debug)]
pub struct Member {
    id: String,
    name: String,
    join_time: NaiveDateTime,
}

impl Member {
    /// return [id, name]
    pub fn to_arr(&self) -> [&String; 2] {
        [&self.id, &self.name]
    }

    pub fn id(&self) -> &String {
        &self.id
    }

    pub fn new(id: &String, name: &String) -> Self {
        Self {
            id: id.clone(),
            name: name.clone(),
            join_time: chrono::Local::now().naive_local(),
        }
    }
    pub fn new_with_time(id: &String, name: &String, time: &String) -> Self {
        let time = NaiveDateTime::parse_from_str(time, "%Y-%m-%d %H:%M:%S%.f").unwrap();
        Self {
            id: id.clone(),
            name: name.clone(),
            join_time: time,
        }
    }

    pub fn to_row(&self) -> Row {
        let time = self.join_time.to_string();
        row![&self.id, &self.name, &time]
    }
}

#[derive(Debug)]
pub struct Team {
    id: i32,
    name: String,
    member_1: String,
    member_2: String,
    member_3: String,
    coach_id: i32,
}

impl Team {
    pub fn new(
        id: i32,
        name: String,
        member_1: String,
        member_2: String,
        member_3: String,
        coach_id: i32,
    ) -> Self {
        Team {
            id,
            name,
            member_1,
            member_2,
            member_3,
            coach_id,
        }
    }
    pub fn to_row(&self) -> Row {
        row![
            &self.id,
            &self.name,
            &self.member_1,
            &self.member_2,
            &self.member_3,
            &self.coach_id
        ]
    }

    // pub fn to_arr(&self) -> [&str; 5] {
    //     // let coach_id_str = ;
    //     [
    //         // &self.id.to_string(),
    //         &self.name,
    //         &self.member_1,
    //         &self.member_2,
    //         &self.member_3,
    //         &self.coach_id.to_string(),
    //     ]
    // }
    pub fn to_arr_without_id(&self) -> [Cow<str>; 5] {
        [
            Cow::Borrowed(&self.name),
            Cow::Borrowed(&self.member_1),
            Cow::Borrowed(&self.member_2),
            Cow::Borrowed(&self.member_3),
            Cow::Owned(self.coach_id.to_string()),
        ]
    }
    pub fn to_arr(&self) -> [Cow<str>; 6] {
        [
            Cow::Borrowed(&self.name),
            Cow::Borrowed(&self.member_1),
            Cow::Borrowed(&self.member_2),
            Cow::Borrowed(&self.member_3),
            Cow::Owned(self.coach_id.to_string()),
            Cow::Owned(self.id.to_string()),
        ]
    }

    pub fn set_id(&mut self, id: i32) {
        self.id = id;
    }
}

#[derive(Debug)]
enum ContestType {
    Online(String),
    Lan(String),
}

#[derive(Debug)]
struct Contest {
    id: i32,
    name: String,
    time: NaiveDateTime,
    in_person: bool,
    contest_type: ContestType,
}

#[derive(Debug)]
pub struct Coach {
    id: i32,
    name: String,
}

impl Coach {
    pub fn new(id: i32, name: String) -> Self {
        Coach { id, name }
    }

    pub fn to_arr(&self) -> [Cow<str>; 2] {
        [Cow::Owned(self.id.to_string()), Cow::Borrowed(&self.name)]
    }

    pub fn to_row(&self) -> Row {
        row![&self.id, &self.name]
    }
}

#[derive(Debug)]
enum ParticipantType {
    InPerson(String),
    Team(i32),
}

#[derive(Debug)]
struct Participant {
    contest_id: i32,
    participant_type: ParticipantType,
}

#[derive(Debug)]
struct Status {
    member_id: String,
    last_login: NaiveDateTime,
}

// #[derive(Debug)]
// struct Record {
//     member_id: String,
//     login_time: NaiveDateTime,
//     logout_time: NaiveDateTime,
// }
