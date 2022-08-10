package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.codegym.model.Song;
import vn.codegym.service.ISongService;

@Controller
@RequestMapping("song")
public class SongController {
    @Autowired
    ISongService songService;
    @GetMapping("")
    public String list(Model model){
        model.addAttribute("song",songService.findAll());
        return "list";
    }
    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("song", new Song());
        return "create";
    }
    @PostMapping("save")
    public String save(Song song){
        songService.save(song);
        return "redirect:/song";
    }
    @GetMapping("{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("song", songService.findById(id));
        return "edit";
    }
    @PostMapping("edit")
    public String edit(Song song){
        songService.update(song);
        return "redirect:/song";
    }
    @GetMapping("{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("song", songService.findById(id));
        return "view";
    }
    @GetMapping("{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("song", songService.findById(id));
        return "delete";
    }
    @PostMapping("delete")
    public String delete(Song song){
        songService.delete(song.getId());
        return "redirect:/song";
    }
}
