//Nome do pacote padrão
package br.com.biblioteca.Graduation.emprestimo;

//Impots de bibliotecas necessárias para a interface
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Uma interface é responsensavel por definir os métodos que terão uma classe. 
 * Nesse caso ela está extendendo do JPaRepository oque diz que essa interface possuí métodos de banco de dados.
 */
public interface IEmprestimoRepository extends JpaRepository<EmprestimoModel, UUID> {
    EmprestimoModel findByEID(String idEmprestimo);
}
