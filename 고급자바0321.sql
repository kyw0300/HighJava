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

-- VO��ü�� ��������� �ڵ����� �����(DB�� Table�� ���õ� VO�����)
-- private �ڷ��� �̸� �÷���;
select 'private ' ||
    decode( lower(data_type), 'number', 'int ', 'String ') ||
    lower( column_name) || ';'
  from cols
where lower(table_name) = 'lprod' ;

