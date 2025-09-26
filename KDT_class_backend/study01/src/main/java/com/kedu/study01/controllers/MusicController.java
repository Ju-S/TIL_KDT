package com.kedu.study01.controllers;

import com.kedu.study01.dto.MusicDTO;
import com.kedu.study01.services.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
public class MusicController {

    // REST ( REpresentational State Transfer)
    // 1. URL 상에 행동을 언급하지 않음 -> 오로지 자원만을 특정
    // 2. 해당 주소에 대해 하고 싶은 행동은 method 로 지정한다.

    private final MusicService musicService;

    @GetMapping
    public ResponseEntity<List<MusicDTO>> getAll() {
        return ResponseEntity.ok(musicService.selectAll());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody MusicDTO dto) {
        musicService.insert(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{targetId}")
    public ResponseEntity<Void> put(@RequestBody MusicDTO dto, @PathVariable int targetId) {
        musicService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{targetId}")
    public ResponseEntity<Void> delete(@PathVariable int targetId) {
        musicService.delete(targetId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> error() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
