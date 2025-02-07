package com.wonders.security.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonders.framework.controller.AbstractCrudController;
import com.wonders.framework.repository.MyRepository;
import com.wonders.security.entity.Account;
import com.wonders.security.entity.Role;
import com.wonders.security.repository.AccountRepository;
import com.wonders.security.service.AccountService;

@Controller
@RequestMapping("accounts")
public class AccountController extends AbstractCrudController<Account, Long> {

	@Inject
	private AccountRepository accountRepository;

	@Inject
	private AccountService accountService;

	@Override
	protected MyRepository<Account, Long> getRepository() {
		return accountRepository;
	}

	@RequestMapping(value = "findByUserId", method = RequestMethod.GET)
	protected @ResponseBody
	List<Account> findByUserId(@RequestParam long userId) {
		return accountRepository.findByUserId(userId);
	}
	
	// adder:zhuhaijian,20130903
	@RequestMapping(value = "findAccountByUserId/{userId}", method = RequestMethod.GET)
	protected @ResponseBody
	List<Account> findAccountByUserId(@PathVariable long userId) {
		return accountRepository.findByUserId(userId);
	}
	
	@RequestMapping(value = "findAccountByAccountId/{accountId}", method = RequestMethod.GET)
	protected @ResponseBody
	List<Account> findAccountByAccountId(@PathVariable long accountId) {
		return accountRepository.findAccountByAccountId(accountId);
	}

	@RequestMapping("addRolesToAccount")
	protected @ResponseBody
	String addRolesToAccount(@RequestParam long accountId, 
			@RequestParam long... roleIds) {
		
		accountService.addRolesToAccount(accountId, roleIds);
		return "{success: true}";
	}

	@RequestMapping(value = "removeRolesFromAccount")
	protected @ResponseBody
	String removeRolesFromAccount(@RequestParam long accountId,
			@RequestParam long... roleIds) {
		
		accountService.removeRolesFromAccount(accountId, roleIds);
		return "{success: true}";
	}
	
	@RequestMapping(value = "findByUserLoginName", method = RequestMethod.GET)
	protected @ResponseBody
	List<Account> findByUserLoginName(@RequestParam String loginName) {
		return accountRepository.findByUserLoginName(loginName);
	}
	
	@RequestMapping(value = "findByUserLoginName/{loginName}", method = RequestMethod.GET)
	protected @ResponseBody
	List<Account> findByUserLoginNameByPath(@PathVariable String loginName) {
		return accountRepository.findByUserLoginName(loginName);
	}
	
	@RequestMapping(value = "findByGroupId", method = RequestMethod.GET)
	protected @ResponseBody
	List<Account> findByGroupId(@RequestParam long groupId) {
		return accountRepository.findByGroupId(groupId);
	}
	
	@RequestMapping(value = "addAccountAuthority")
	protected @ResponseBody
	String addAccountAuthority(@RequestParam long accountId, 
			@RequestParam(required = false) long... authIds) {
		accountService.addAccountAuthority(accountId, authIds);
		return "{success: true}";
	}
	
	@RequestMapping(value = "findAccountAuthority", method = RequestMethod.GET)
	protected @ResponseBody
	String findAccountAuthority(@RequestParam long accountId){
		return accountService.findAccountAuthority(accountId);
	}
	
	// adder:zhuhaijian,20130904
	@RequestMapping(value = "findAccountAuthorityByAccountId/{accountId}", method = RequestMethod.GET)
	protected @ResponseBody
	String findAccountAuthorityByAccountId(@PathVariable long accountId){
		return accountService.findAccountAuthority(accountId);
	}
	
	@RequestMapping(value = "findAccountRoleByAccountId/{accountId}", method = RequestMethod.GET)
	protected @ResponseBody
	String findAccountRoleByAccountId(@PathVariable long accountId){
		return accountService.findAccountRole(accountId);
	}
	
	@RequestMapping(value = "findAccountGroupByAccountId/{accountId}", method = RequestMethod.GET)
	protected @ResponseBody
	String findAccountGroupByAccountId(@PathVariable long accountId){
		return accountService.findAccountGroup(accountId);
	}
	
	@RequestMapping(value = "findByUserLoginNameNot", method = RequestMethod.GET)
	protected @ResponseBody
	List<Account> findByUserLoginNameNot(@RequestParam String loginName){
		return accountRepository.findByUserLoginNameNot(loginName);
	}
	
	@RequestMapping(value = "validateAccount", method = RequestMethod.GET)
	protected @ResponseBody
	String validateAccount(@RequestParam String name,
			@RequestParam long userId, @RequestParam long groupId){
		List<Account> list = accountRepository.validateAccout(name, groupId, userId);
		if(list.size() == 0 ){
			return "{success: true}";
		}else {
			return "{success: false}";
		}
	}

	// adder:zhuhaijian,20130903,查询所有协调员
	@RequestMapping(value = "findAllAccount/{accountId}", method = RequestMethod.GET)
	protected @ResponseBody
	List<Account> findAllAccount(@PathVariable long accountId) {
		return accountRepository.findAllAccount(accountId);
	}
	
	
	@RequestMapping(value = "validateAccountExist", method = RequestMethod.GET)
	protected @ResponseBody
	String validateAccountExist(@RequestParam String name,
			@RequestParam long userId, @RequestParam long groupId, @RequestParam long accId){
		List<Account> list = accountRepository.validateAccout(name, groupId, userId);
		if(list.size() == 0 ){
			return "{success: true}";
		}else {
			if(accId == list.get(0).getId()) {
				return "{success: true}";
			}
			return "{success: false}";
		}
	}
	
	@RequestMapping(value = "validateRoleRelationExist", method = RequestMethod.GET)
	protected @ResponseBody
	String validateAccountRelationExist(@RequestParam long roleId){
		System.out.println("it enters the interface");
		List<Account> accounts = accountRepository.findByRoleId(roleId);
		if(accounts == null || accounts.isEmpty()){
			return "{success: true}";
		}else{
			System.out.println("accountSize: "+accounts.size());
			return "{success: false}";
		}
	}
}
