package br.com.biblioteca.Graduation.emprestimo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.List;

/**
 * Um controller é responsável por receber as requisições da internet e devolver uma resposta.
 * @Rotas 
 * /users - rota principal, ela é a padrão então o endpoint dessa classe é localhost:8080/users/alguma coisa
 * /create POST- rota onde você vai criar um usuário
 * /list-users GET - rota que lista todos os usuários
 */

// Rest Controller é uma parâmetro que indica que a classe é um controller
@RestController
// RequestMapping é um parâmetro que cria um endpoint 
@RequestMapping("/emprestimos")
public class EmprestimoController {

    // Autowired indica que o Spring irá instanciar o objeto automaticamente basicamente
    @Autowired
    private IEmprestimoRepository EmprestimoRepository;
    
    // @PostMapping e @GetMapping indicam que o método abaixo se comporta como uma requisição POST ou GET
    @PostMapping("/create")
    public ResponseEntity create (@RequestBody EmprestimoModel EmprestimoModel){
        // Cria uma variável chamada user que irá receber um usuário, quando fazemos this.userRepository estamos acessando aquela classe que possuí os métodos do banco de dados, findByUsername é um método que busca um usuário baseado no nome.
        var Emprestimo = this.EmprestimoRepository.findByEID(EmprestimoModel.getEID());
        
        // Caso não encontre um usuário ele retorna na requisição BAD_REQUEST
        if Emprestimo != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("emprestimo já existe");
        }

        // Cria um novo usuário, pega uma variável chamada userCreated que recebe o retorno do save do userRepository
        var EmprestimoCreated = this.EmprestimoRepository.save(EmprestimoModel);

        // Retorna uma requisição CREATED com o userCreated (usuário criado)
        return ResponseEntity.status(HttpStatus.CREATED).body(EmprestimoCreated);
    }

    @GetMapping("/list-emprestimos")
    // Cria um método do tipo do tipo ResponseEntity onde ele tem como retorno uma lista de usuários
    public ResponseEntity<List<EmprestimoModel>> getAllEmprestimos() {
        // cria um array de usuários onde ele recebe todos os usuários do método findAll(), esse método é a mesma coisa que um SELECT * FROM tb_users;
        List<EmprestimoModel> emprestimos = EmprestimoRepository.findAll();
        if (Emprestimo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(livros);
    }

    @PostMapping("/authenticate")
    // Cria um método do tipo do tipo ResponseEntity que retorna um objeto ou seja um JSON{} e recebe um usuário
    public ResponseEntity<Object> authenticate(@RequestBody EmprestimoModel registro) {
        // cria um usuário que irá buscar atravez do username
        EmprestimoModel Emprestimo = this.EmprestimoRepository.findByEID(registro.getEID());
    }
}
