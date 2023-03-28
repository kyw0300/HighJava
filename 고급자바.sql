create table bankinfo(
    bank_no varchar2(40) not null,         -- ���¹�ȣ
    bank_name varchar2(40) not null,       -- �����̸�
    bank_user_name varchar2(30) not null,  -- ������ �̸�
    bank_date date not null,               -- ������¥
    constraint pk_bankinfo primary key (bank_no)
);

insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date)
     values();
     
select max(lprod_id) from lprod;

select count(*) from lprod
 where lprod_gu='p102';
 
create table mymember(
    mem_id varchar2(20) not null,       -- ȸ��ID
    mem_pass varchar2(30) not null,     -- ��й�ȣ
    mem_name varchar2(30) not null,     -- ȸ���̸�
    mem_tel varchar2(14),               -- ��ȭ��ȣ
    mem_addr varchar2(90) not null,     -- �ּ�
    constraint pk_mymember primary key (mem_id)
);

delete from mymember where MEM_NAME = 'a000';

select MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR from mymember;

update mymember set MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? where mem_id = ?
commit;

update mymember set MEM_PASS = ? where mem_id = 

select lpad(MEM_ID,15), lpad(MEM_PASS,15), lpad(MEM_NAME,15), lpad(MEM_TEL,20), lpad(MEM_ADDR,25) from mymember;

create table jdbc_board(
    board_no number not null,  -- ��ȣ(�ڵ�����)
    board_title varchar2(100) not null, -- ����
    board_writer varchar2(50) not null, -- �ۼ���
    board_date date not null,   	-- �ۼ���¥
    board_cnt number default 0, -- ��ȸ��
    board_content clob,     	-- ����
    constraint pk_jdbc_board primary key (board_no)
);

create sequence board_seq
    start with 1   -- ���۹�ȣ
    increment by 1; -- ������
    
insert into jdbc_board(BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_DATE,BOARD_CONTENT)
values(board_seq.nextval,'������','�ۼ���',sysdate,'������ ��¼�� ��¼��');

select BOARD_TITLE,BOARD_WRITER,BOARD_CONTENT,BOARD_DATE,BOARD_CNT from JDBC_BOARD where BOARD_NO=4;
select * from JDBC_BOARD;

update JDBC_BOARD set BOARD_TITLE = ?, BOARD_CONTENT = ? where BOARD_NO = ?

update JDBC_BOARD set BOARD_CNT = BOARD_CNT+1 where BOARD_NO = 4;

select BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_CNT from JDBC_BOARD where BOARD_TITLE like '%ù��°%'; 
