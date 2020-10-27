export class Enrollee {
  constructor(	public name: string, 
			  	public dateOfBirth: string, 
				public id: string, 
				public update: boolean,				
				public active: boolean) {}
				
	get status() : string{
		
		return this.active ? 'Active' : 'Inactive'
	}
	
	set status(value : string){
		
		this.active =  value == 'Active' ? true : false
	}
}