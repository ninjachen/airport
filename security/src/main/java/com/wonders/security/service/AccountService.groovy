package com.wonders.security.service

import javax.inject.Inject

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.wonders.security.entity.Account
import com.wonders.security.entity.Role;
import com.wonders.security.repository.AccountRepository
import com.wonders.security.repository.AuthorityRepository;
import com.wonders.security.repository.RoleRepository

@Service
class AccountService {
	
	@Inject
	private AccountRepository accountRepository
	
	@Inject
	private RoleRepository roleRepository
	
	@Inject
	private AuthorityRepository authorityRepository

	@Transactional
	Account addRolesToAccount(long accountId, long... roleIds) {
		
		def account = accountRepository.findOne(accountId)
		
		if (account) {
			
			def roles = roleRepository.findAll(roleIds as List)
			
			account.roles.addAll(roles)
		}
		
		account
	}
	
	@Transactional
	Account removeRolesFromAccount(long accountId, long... roleIds) {
		
		def account = accountRepository.findOne(accountId)
		
		if (account) {
			
			def roles = roleRepository.findAll(roleIds as List)
			
			account.roles.removeAll(roles)
		}
		
		account
	}
	
	@Transactional
	Account addAccountAuthority(long accountId, long... authIds) {
		
		def account = accountRepository.findOne(accountId)
		
		if (account) {
			def auths = authorityRepository.findAll(authIds as List)
			
			account.authorities = auths
		}
		account
	}
	
	@Transactional(readOnly = true)
	String findAccountAuthority(long accountId) {
		
		def account = accountRepository.findOne(accountId)
		
		(account.authorities*.id).join(',')
	}
	
	@Transactional(readOnly = true)
	String findAccountRole(long accountId) {
		
		def account = accountRepository.findOne(accountId)
				
				(account.roles*.name).join(',')
	}
	
	@Transactional(readOnly = true)
	String findAccountGroup(long accountId) {
		
		def account = accountRepository.findOne(accountId)
				
				account.group.name
	}

}
