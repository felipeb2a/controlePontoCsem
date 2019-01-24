-- SELECT FUNCIONARIO
select f.idFuncionario, f.nome from funcionario f;

-- SELECT PONTO
select * from ponto p inner join pontomes pm on pm.idPontoMes = p.fkPontoMes inner join funcionario f on f.idFuncionario = p.fkFuncionario where pm.mes = 10;