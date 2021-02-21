package com.produtos.apirest.resources;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Produtos")
public class ProdutoResource {

    @Autowired
    ProdutoRepository produtoRepository;

    @ApiOperation(value = "Retorna uma lista de produtos")
    @GetMapping("/produtos")
    public List<Produto> listaProdutos() {
        return produtoRepository.findAll();
    }

    @ApiOperation(value = "Retorna informações de um unico produto")
    @GetMapping("/produto/{id}")
    public com.produtos.apirest.Interfaces.Produto request(@PathVariable(value = "id") long id) {
        return produtoRepository.findById(id);
    }

    @ApiOperation(value = "Salva um produto")
    @ApiImplicitParam
    @PostMapping("/produto")
    public ResponseEntity register(@RequestBody Produto produto) {
        produtoRepository.save(produto);

        Map<String, String> json = new HashMap<String, String>();
        json.put("message", "Produto cadastrado com sucesso!");

        return new ResponseEntity(json, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Deleta um produto")
    @DeleteMapping("/produto/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") long id) {
        Boolean exist = produtoRepository.existsById(id);

        if(!exist) {
            Map<String, String> json = new HashMap<String, String>();
            json.put("message", "Produto não encontrado!");

            return new ResponseEntity(json, HttpStatus.NOT_FOUND);
        }

        produtoRepository.deleteById(id);

        Map<String, String> json = new HashMap<String, String>();
        json.put("message", "Produto deletado com sucesso!");

        return new ResponseEntity(json, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Atualiza um produto")
    @PutMapping("/produto/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Produto produto) {
        Boolean exist = produtoRepository.existsById(id);

        if(!exist) {
            Map<String, String> json = new HashMap<String, String>();
            json.put("message", "Produto não encontrado!");

            return new ResponseEntity(json, HttpStatus.NOT_FOUND);
        }

        produtoRepository.save(produto);

        Map<String, String> json = new HashMap<String, String>();
        json.put("message", "Produto atualizado com sucesso!");

        return new ResponseEntity(json, HttpStatus.ACCEPTED);
    }
}
