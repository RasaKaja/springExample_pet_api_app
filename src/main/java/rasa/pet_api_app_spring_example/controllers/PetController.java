package rasa.pet_api_app_spring_example.controllers;

import org.springframework.web.bind.annotation.*;
import rasa.pet_api_app_spring_example.models.Pet;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("api/pets")
public class PetController {

    private final ArrayList<Pet> pets = new ArrayList<>();

/*CRUD functions*/
    /*CREATE*/
    @PostMapping
    //@PostMapping("api/pets") // when is @RequestMapping like in 9 line, we can use like 16 like
    public Pet createPet(@RequestBody Pet petRequest){
        Pet newPet = new Pet(
                petRequest.getName(),
                petRequest.getAge(),
                petRequest.getType(),
                petRequest.getOwnerName()
        );
        pets.add(newPet);
        return newPet;
    }

    /*READ*/
    @GetMapping
    public ArrayList<Pet> getPets() {
        return pets;
    }

    /*UPDATE*/
    @PutMapping(path = "{id}")
    public void updatePetById(@PathVariable("id") UUID id, @RequestBody Pet petToUpdate) {
        Pet pet = selectPetById(id);
        int indexOfPetToUpdate = pets.indexOf(pet);
        if (indexOfPetToUpdate >= 0){
            pets.set(indexOfPetToUpdate, petToUpdate);
            System.out.println(pet.getName() + " was updated successfully.");
        }
        System.out.println("Pet not found.");
    }

    /*DELETE*/
    @DeleteMapping(path = "{id}")
    public void deletePetById(@PathVariable("id") UUID id){
        Pet petForDelete = selectPetById(id);
        if (petForDelete == null){
            System.out.println("There is no Pet with such Id");
        }
        pets.remove(petForDelete);
        System.out.println(petForDelete.getName() + " was deleted successfully");
    }

    @GetMapping(path = "{id}")
    public Pet getPetById(@PathVariable("id") UUID id){
        return selectPetById(id);
    }

/*(@PathVariable("id") UUID id) --> grabbing id from @PathVariable("id"), and returning it to UUID id*/
    Pet selectPetById(UUID id) {
        for (Pet pet: pets){
            if (id.equals(pet.getId())){
                return pet;
            }
        }
        return null;
    }
}