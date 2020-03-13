package br.first.tte.exception;

public class Validator extends RuntimeException{
	public Validator(String message) {
		super(message);
	}
}
