package com.example.student_mark_portal_day18.service.impl;


import com.example.student_mark_portal_day18.dao.MarksDAO;
import com.example.student_mark_portal_day18.dto.MarksDTO;
import com.example.student_mark_portal_day18.exception.ResourceNotFoundException;
import com.example.student_mark_portal_day18.mapper.MarksMapper;
import com.example.student_mark_portal_day18.model.Marks;
import com.example.student_mark_portal_day18.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MarksServiceImpl implements MarksService {

    @Autowired
    private MarksDAO marksDAO;

    @Autowired
    private MarksMapper marksMapper;

    @Override
    public MarksDTO createMarks(MarksDTO marksDTO) {
        Marks marks = marksMapper.toEntity(marksDTO);
        Marks savedMarks = marksDAO.save(marks);

        return marksMapper.toDTO(savedMarks);
    }

    @Override
    @Transactional(readOnly = true)
    public MarksDTO getMarksById(int id) {

        return marksDAO.findById(id)
                .map(marksMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Marks not found with id: " + id));
    }

    @Override
    public MarksDTO updateMarks(int id, MarksDTO marksDTO) {

        if (!marksDAO.existsById(id)) {
            throw new ResourceNotFoundException("Marks not found with id: " + id);

        }
        Marks marks = marksMapper.toEntity(marksDTO);
        marks.setMarksId(id);
        Marks updatedMarks = marksDAO.save(marks);

        return marksMapper.toDTO(updatedMarks);
    }

    @Override
    public void deleteMarks(int id) {

        if (!marksDAO.existsById(id)) {
            throw new ResourceNotFoundException("Marks not found with id: " + id);

        }
        marksDAO.deleteById(id);
    }
}
