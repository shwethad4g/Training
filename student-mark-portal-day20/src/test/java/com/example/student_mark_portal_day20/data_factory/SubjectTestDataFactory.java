package com.example.student_mark_portal_day20.data_factory;

import com.example.student_mark_portal_day20.model.Subject;

public class SubjectTestDataFactory {

    public static Subject createSubject(Integer id, String name) {
        Subject subject = new Subject();
        subject.setSubjectId(id);
        subject.setName(name);
        return subject;
    }

    public static Subject createDefaultSubject() {
        return createSubject(1, "Mathematics");
    }

    public static Subject createPhysicsSubject() {
        return createSubject(1, "Physics");
    }

    public static Subject createChemistrySubject() {
        return createSubject(2, "Chemistry");
    }
}
