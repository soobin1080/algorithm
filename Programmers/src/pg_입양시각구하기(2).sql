
-- 로컬변수 활용하는 문제
SET @hour := -1;
SELECT(@hour := @hour+1) as HOUR,(
SELECT COUNT(*) FROM ANIMAL_OUTS WHERE HOUR(DATETIME) = @hour) as COUNT 
FROM ANIMAL_OUTS WHERE @hour<23

-- @ 가 붙는 변수는 프로시저가 종료되도 유지 --> 누적하여 0~ 23까지
-- sql 에서는 := 가 = 연산자랑 같은 표기