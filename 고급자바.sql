create table bankinfo(
    bank_no varchar2(40) not null,         -- 계좌번호
    bank_name varchar2(40) not null,       -- 은행이름
    bank_user_name varchar2(30) not null,  -- 예금주 이름
    bank_date date not null,               -- 개설날짜
    constraint pk_bankinfo primary key (bank_no)
);

insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date)
     values();
     
select max(lprod_id) from lprod;

select count(*) from lprod
 where lprod_gu='p102';
 
create table mymember(
    mem_id varchar2(20) not null,       -- 회원ID
    mem_pass varchar2(30) not null,     -- 비밀번호
    mem_name varchar2(30) not null,     -- 회원이름
    mem_tel varchar2(14),               -- 전화번호
    mem_addr varchar2(90) not null,     -- 주소
    constraint pk_mymember primary key (mem_id)
);

delete from mymember where MEM_NAME = 'a000';

select MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR from mymember;

update mymember set MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? where mem_id = ?
commit;

update mymember set MEM_PASS = ? where mem_id = 

select lpad(MEM_ID,15), lpad(MEM_PASS,15), lpad(MEM_NAME,15), lpad(MEM_TEL,20), lpad(MEM_ADDR,25) from mymember;

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
    
insert into jdbc_board(BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_DATE,BOARD_CONTENT)
values(board_seq.nextval,'글제목','작성자',sysdate,'내용이 어쩌구 저쩌구');

select BOARD_TITLE,BOARD_WRITER,BOARD_CONTENT,BOARD_DATE,BOARD_CNT from JDBC_BOARD where BOARD_NO=4;
select * from JDBC_BOARD;

update JDBC_BOARD set BOARD_TITLE = ?, BOARD_CONTENT = ? where BOARD_NO = ?

update JDBC_BOARD set BOARD_CNT = BOARD_CNT+1 where BOARD_NO = 4;

select BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_CNT from JDBC_BOARD where BOARD_TITLE like '%첫번째%'; 
