-- 코드를 입력하세요
SELECT ANIMAL_OUTS.ANIMAL_ID, ANIMAL_OUTS.NAME
FROM ANIMAL_OUTS, ANIMAL_INS
WHERE ANIMAL_OUTS.ANIMAL_ID=ANIMAL_INS.ANIMAL_ID
	AND ANIMAL_OUTS.DATETIME < ANIMAL_INS.DATETIME
ORDER BY ANIMAL_INS.DATETIME

-- 코드를 입력하세요
SELECT ANIMAL_OUTS.ANIMAL_ID, ANIMAL_OUTS.NAME
FROM ANIMAL_OUTS LEFT OUTER JOIN ANIMAL_INS
	ON ANIMAL_OUTS.ANIMAL_ID=ANIMAL_INS.ANIMAL_ID
WHERE ANIMAL_OUTS.DATETIME < ANIMAL_INS.DATETIME
ORDER BY ANIMAL_INS.DATETIME