-- INSERT
insert into funcionario (`idFuncionario`, `nome`) VALUES ('1', 'Felipe Ferreira');

-- SELECT FUNCIONARIO
select f.idFuncionario, f.nome from funcionario f;

-- SELECT PONTO
select * from ponto p inner join pontomes pm on pm.idPontoMes = p.fkPontoMes inner join funcionario f on f.idFuncionario = p.fkFuncionario where pm.mes = 10 and f.idFuncionario = 1;

update ponto set horaExtraDia = 1, minutoExtraDia = 37 where idPonto = 1 and fkFuncionario = 3;
