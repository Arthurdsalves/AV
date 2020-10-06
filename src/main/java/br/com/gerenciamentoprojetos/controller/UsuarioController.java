package br.com.gerenciamentoprojetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.gerenciamentoprojetos.model.Usuario;
import br.com.gerenciamentoprojetos.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuario/list")
	public String listUsuario(Model model) {
		
		model.addAttribute("usuarios", usuarioRepository.findAll(Sort.by("idUsuario")));
		return "usuario/list";
	}
	
	@GetMapping("/usuario/add")
	public String addUsuario(Model model) {
		try {
			model.addAttribute("usuario", new Usuario());
			
		}catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
			
		}
		
		return "usuario/add";
		
	}
	
	@PostMapping("/usuario/save")
	public String saveUsuario(Usuario usuario) {
		try {
			usuarioRepository.save(usuario);
			
		}catch(Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return "redirect:/usuario/view/" + usuario.getIdUsuario();
	}
	
	@GetMapping("/usuario/view/{idUsuario}")
	public String viewUsuario(@PathVariable long idUsuario, Model model) {
		model.addAttribute("usuario", usuarioRepository.findById(idUsuario));
		return "usuario/view";
	}
	
	@GetMapping("/usuario/edit/{idUsuario}")
	public String editUsuario(@PathVariable long idUsuario, Model model) {
		model.addAttribute("usuario", usuarioRepository.findById(idUsuario));
		return "usuario/edit";
	}
	
	@GetMapping("/usuario/delete/{idUsuario}")
	public String deleteUsuario(@PathVariable long idUsuario, Model model) {
		try {
			usuarioRepository.deleteById(idUsuario);
		}catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return "redirect:/usuario/list";
		
	}
	
}
