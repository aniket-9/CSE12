/***************************************************************************
% Name: Ji Woon Chung
% Login: cs12xbo
% Date: February 10, 2015
% Class: CSE 12, Winter 2015
%
% Assignment Six
% File Name: Driver.c 
% Description: A file filled with methods needed to test, run, and
% check if Hash.c works properly. Converts the strings
% that the user has inputted and turns it into an ASCII sum. Has constructors
% that creates  multiple names and student number every time the user inputs
% a new name and number. 
***************************************************************************/

#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <getopt.h>
#include <iostream>
#include "Base.h"
#include "SymTab.h"
using namespace std;

#ifdef NULL
#undef NULL
#define NULL 0
#endif


class UCSDStudent : public Base {
	char * name;
	long studentnum;
public:
	UCSDStudent (char * nm, long sn) :
		name (strdup (nm)), studentnum (sn) {}
	~UCSDStudent (void) {
		/* destructor for the name */
		free (name);
	}

	/*******************************************************************
	% Routine Name : operator char * (public)
	% File :         Driver.c
	% 
	% Description :  Returns the name that the user has inputted.
	%
	% Parameters descriptions :
	% 
	% name		Description
	% -------------- ---------------------------------------------------
	% void          A character that is nothing
	*******************************************************************/
	operator char * (void) {
		return name;
	}

	/*******************************************************************
	% Routine Name : operator char * (public)
	% File :         Driver.c
	% 
	% Description :  To see if the name and student number is 
	%		 similar that of the the names and student numbers
	%		 that are stored.
	%
	% Parameters descriptions :
	% 
	% name		Description
	% -------------- ---------------------------------------------------
	% void          A long value that is nothing. 
	*******************************************************************/
	operator long (void);
	long operator == (Base & base) {
		return ! strcmp (name, base);
	}


	ostream & Write (ostream & stream) {
		return stream << "name: " << name
			<< " Studentnum: " << studentnum;
	}

};

/*******************************************************************
% Routine Name : UCSDStudent :: operator long (public)
% File :         Driver.c
% 
% Description :  A function that converts the name that the user has 
%		 inputted in to the terminal into an ASCII and sums
%		 it up to get a total. 
%
% Parameters descriptions :
% 
% name		Description
% -------------- ---------------------------------------------------
% void          A long value that is nothing. 
*******************************************************************/	
UCSDStudent :: operator long (void) {
	long sum = 0;
	/* a for loop that adds up the ASCII for each character of
	 * the name that the user has inputted */
	for (long index = 0; name[index]; index++)
		sum += name[index];
	return sum;
}

int main (int argc, char * const * argv) {
	SymTab ST (5);
	char buffer[128];
	char command;
	long number;
	char option;

	/* initialize debug variable */
	HashTable::Set_Debug(0);

	 /* check command line options for debug display */
	 while ((option = getopt (argc, argv, "x")) != EOF) {

		 switch (option) {
			 case 'x': HashTable::Set_Debug(1);
				 break;
		 	}
	} 

	ST.Write (cout << "Initial Symbol Table:\n");
	while (cin) {
		command = NULL;	// reset command each time in loop
		cout << "Please enter a command:  ((i)nsert, (l)ookup, (w)rite):  ";
		cin >> command;

		switch (command) {
		case 'i':
			cout << "Please enter UCSD student name to insert:  ";
			cin >> buffer;	// formatted input

			cout << "Please enter UCSD student number:  ";
			cin >> number;

			// create student and place in symbol table
			if(!ST.Insert (new UCSDStudent (buffer, number))){

				ST.Write (cout << "\nFinal Symbol Table:\n");
				exit (0);
			}
			break;

		case 'l': {
			Base * found;	// whether found or not

			cout << "Please enter UCSD student name to lookup:  ";
			cin >> buffer;	// formatted input

			UCSDStudent stu (buffer, 0);
			found = ST.Lookup (& stu);
			
			if (found)
				found->Write (cout << "Student found!!!\n") << "\n";
			else
				cout << "Student " << buffer << " not there!\n";
			}
			break;

		case 'w':
			ST.Write (cout << "The Symbol Table contains:\n");
		}
	}
	ST.Write (cout << "\nFinal Symbol Table:\n");
}

