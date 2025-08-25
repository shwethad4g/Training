package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.DeleteAccountDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.DeleteAccountResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.DeleteAccountResponseMapper;
import com.example.Personal_Finance_Tracker_Final_Project.service.DeleteAccountService;
import org.springframework.stereotype.Service;

@Service
@lombok.RequiredArgsConstructor
public class DeleteAccountServiceImpl implements DeleteAccountService {

    private final DeleteAccountDAO deleteAccountDAO;
    private final DeleteAccountResponseMapper responseMapper;

    @Override
    @org.springframework.transaction.annotation.Transactional
    public DeleteAccountResponseDTO deleteAccount(Long userId, String token) {

        deleteAccountDAO.assertUserExists(userId);
        deleteAccountDAO.blacklistTokenSafe(token, userId);
        deleteAccountDAO.deleteUserGraph(userId);

        return responseMapper.toDTO("Account successfully deleted");
    }
}
