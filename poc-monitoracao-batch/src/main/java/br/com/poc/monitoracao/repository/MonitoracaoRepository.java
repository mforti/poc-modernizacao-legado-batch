
package br.com.poc.monitoracao.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.poc.monitoracao.dominio.MonitoracaoRedis;


/** 
 * Interface para o repositorio do MonitoracaoRedis.
 * @author mforti
 *
 */
public interface MonitoracaoRepository extends CrudRepository<MonitoracaoRedis, String>{

}
