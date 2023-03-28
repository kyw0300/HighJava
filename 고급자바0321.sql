create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   	-- 작성날짜
    board_cnt number default 0, -- 조회수
    board_content clob,     	-- 내용
    constraint pk_jdbc_board primary key (board_no)
);

create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값

-- VO객체의 멤버변수를 자동으로 만들기(DB의 Table과 관련된 VO만들기)
-- private 자료형 이름 컬럼명;
select 'private ' ||
    decode( lower(data_type), 'number', 'int ', 'String ') ||
    lower( column_name) || ';'
  from cols
where lower(table_name) = 'lprod' ;

