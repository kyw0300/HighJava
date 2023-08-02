select nvl(max(lprod_id),0)+1 from lprod

select BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_CNT from JDBC_BOARD order by 1 desc
select * from JDBC_BOARD order by BOARD_NO desc
select BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_CNT from JDBC_BOARD order by BOARD_NO desc
select * from JDBC_BOARD
select * from JDBC_BOARD where BOARD_NO = 10

select buyer_id, buyer_name from buyer;
select * from buyer where buyer_id = 'P10101';

select * from lprod;
select prod_id,prod_name from prod where prod_lgu = 'P201';
select * from prod where prod_id = ??

select * from Á÷¿ø where ¿¬ºÀ>=1500
UNION ALL
select * from Á÷¿ø where ¿¬ºÀ>=2000;

select * from MYMEMBER
