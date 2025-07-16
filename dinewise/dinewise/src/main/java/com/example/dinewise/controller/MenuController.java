// package com.example.dinewise.controller;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.dinewise.dto.request.MenuRequestDTO;
// import com.example.dinewise.model.Menu;
// import com.example.dinewise.repo.MenuRepository;
// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @RestController
// @RequestMapping("/menus")
// public class MenuController {

//     @Autowired
//     private MenuRepository menuRepo;

//     // @PostMapping
//     // public ResponseEntity<?> createOrUpdateMenu(@RequestBody Map<String, Object> request) {
//     //     LocalDate menuDate = LocalDate.parse((String) request.get("menuDate"));
//     //     if (menuDate.isBefore(LocalDate.now())) {
//     //         return ResponseEntity.badRequest().body(Map.of("message", "Cannot set menu for past date"));
//     //     }

//     //     List<String> lunchItems = (List<String>) request.get("lunchItems");
//     //     List<String> dinnerItems = (List<String>) request.get("dinnerItems");

//     //     Menu menu = menuRepo.findByMenuDate(menuDate).orElse(new Menu());
//     //     menu.setMenuDate(menuDate);
//     //     menu.setLunchItems(lunchItems);
//     //     menu.setDinnerItems(dinnerItems);
//     //     menuRepo.save(menu);

//     //     return ResponseEntity.ok(Map.of("message", "Menu saved successfully"));
//     // }

//     // @PostMapping
//     // public ResponseEntity<?> createOrUpdateMenu(@RequestBody MenuRequestDTO request) {
//     //     if (request.getMenuDate().isBefore(LocalDate.now())) {
//     //         return ResponseEntity.badRequest().body(Map.of("message", "Cannot set menu for past date"));
//     //     }

//     //     Menu menu = menuRepo.findByMenuDate(request.getMenuDate()).orElse(new Menu());
//     //     menu.setMenuDate(request.getMenuDate());
//     //     menu.setLunchItems(request.getLunchItems());
//     //     menu.setDinnerItems(request.getDinnerItems());

//     //     menuRepo.save(menu);
//     //     return ResponseEntity.ok(Map.of("message", "Menu saved successfully"));
//     // }



//     @Autowired
// private ObjectMapper objectMapper;

// @PostMapping
// public ResponseEntity<?> createOrUpdateMenu(@RequestBody Map<String, Object> request) {
//     LocalDate menuDate = LocalDate.parse((String) request.get("menuDate"));

//     if (menuDate.isBefore(LocalDate.now())) {
//         return ResponseEntity.badRequest().body(Map.of("message", "Cannot set menu for past date"));
//     }

//     List<String> lunchItems = objectMapper.convertValue(request.get("lunchItems"), new TypeReference<List<String>>() {});
//     List<String> dinnerItems = objectMapper.convertValue(request.get("dinnerItems"), new TypeReference<List<String>>() {});

//     Menu menu = menuRepo.findByMenuDate(menuDate).orElse(new Menu());
//     menu.setMenuDate(menuDate);
//     menu.setLunchItems(lunchItems);
//     menu.setDinnerItems(dinnerItems);

//     menuRepo.save(menu);
//     return ResponseEntity.ok(Map.of("message", "Menu saved successfully"));
// }


// }

package com.example.dinewise.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dinewise.dto.request.MenuRequestDTO;
import com.example.dinewise.model.Menu;
import com.example.dinewise.repo.MenuRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuRepository menuRepo;

    // @PostMapping
    // public ResponseEntity<?> createOrUpdateMenu(@RequestBody Map<String, Object> request) {
    //     LocalDate menuDate = LocalDate.parse((String) request.get("menuDate"));
    //     if (menuDate.isBefore(LocalDate.now())) {
    //         return ResponseEntity.badRequest().body(Map.of("message", "Cannot set menu for past date"));
    //     }

    //     List<String> lunchItems = (List<String>) request.get("lunchItems");
    //     List<String> dinnerItems = (List<String>) request.get("dinnerItems");

    //     Menu menu = menuRepo.findByMenuDate(menuDate).orElse(new Menu());
    //     menu.setMenuDate(menuDate);
    //     menu.setLunchItems(lunchItems);
    //     menu.setDinnerItems(dinnerItems);
    //     menuRepo.save(menu);

    //     return ResponseEntity.ok(Map.of("message", "Menu saved successfully"));
    // }

    // @PostMapping
    // public ResponseEntity<?> createOrUpdateMenu(@RequestBody MenuRequestDTO request) {
    //     if (request.getMenuDate().isBefore(LocalDate.now())) {
    //         return ResponseEntity.badRequest().body(Map.of("message", "Cannot set menu for past date"));
    //     }

    //     Menu menu = menuRepo.findByMenuDate(request.getMenuDate()).orElse(new Menu());
    //     menu.setMenuDate(request.getMenuDate());
    //     menu.setLunchItems(request.getLunchItems());
    //     menu.setDinnerItems(request.getDinnerItems());

    //     menuRepo.save(menu);
    //     return ResponseEntity.ok(Map.of("message", "Menu saved successfully"));
    // }


    // @GetMapping
    // public ResponseEntity<?> getMenuByDate(@RequestParam("date") String dateStr) {
    //     try {
    //         LocalDate date = LocalDate.parse(dateStr);
    //         Optional<Menu> optionalMenu = menuRepo.findByMenuDate(date);
    //         if (optionalMenu.isPresent()) {
    //             return ResponseEntity.ok(optionalMenu.get());
    //         } else {
    //             return ResponseEntity.ok(Map.of("message", "No menu found for selected date"));
    //         }
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(Map.of("error", "Invalid date format or internal error"));
    //     }
    // }


    @GetMapping
    public ResponseEntity<?> getMenuByDate(@RequestParam("date") String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return menuRepo.findByMenuDate(date)
                .map(menu -> ResponseEntity.ok(Map.of(
                    "lunchItems", menu.getLunchItems(),
                    "dinnerItems", menu.getDinnerItems()
                )))
                .orElse(ResponseEntity.ok(Map.of(
                    "lunchItems", List.of(),
                    "dinnerItems", List.of()
                )));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid date format or internal error"));
        }
    }


    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<?> createOrUpdateMenu(@RequestBody Map<String, Object> request) {
        LocalDate menuDate = LocalDate.parse((String) request.get("menuDate"));

        if (menuDate.isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Cannot set menu for past date"));
        }

        List<String> lunchItems = objectMapper.convertValue(request.get("lunchItems"), new TypeReference<List<String>>() {});
        List<String> dinnerItems = objectMapper.convertValue(request.get("dinnerItems"), new TypeReference<List<String>>() {});

        Menu menu = menuRepo.findByMenuDate(menuDate).orElse(new Menu());
        menu.setMenuDate(menuDate);
        menu.setLunchItems(lunchItems);
        menu.setDinnerItems(dinnerItems);

        menuRepo.save(menu);
        return ResponseEntity.ok(Map.of("message", "Menu saved successfully"));
    }


}

