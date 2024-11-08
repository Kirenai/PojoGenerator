import { Button } from "@/components/ui/button";
import { Textarea, VStack } from "@chakra-ui/react";
import { Field, Form, Formik } from "formik";

const PojoForm = () => {
  const placeholder = "data.id - I\ndata.name - I\ndata.age - I";

  return (
    <VStack
      data-state="open"
      _open={{
        animationName: "slide-from-top, fade-in",
        animationDuration: "slow",
      }}
      w="96"
    >
      <Formik initialValues={{}} onSubmit={() => {}}>
        {() => (
          <Form>
            <Field
              w="40.40vw"
              h="60.60vh"
              as={Textarea}
              size="xl"
              name="description"
              placeholder={placeholder}
              colorPalette="teal"
            />
            <Button
              type="submit"
              variant="surface"
              colorPalette="teal"
              size="xl"
              w="36"
            >
              Submit
            </Button>
          </Form>
        )}
      </Formik>
    </VStack>
  );
};

export default PojoForm;
