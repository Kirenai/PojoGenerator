import { Button } from "@/components/ui/button";
import { ColorModeButton } from "@/components/ui/color-mode";
import { Box, Heading, VStack } from "@chakra-ui/react";

const HomePage = () => {
  return (
    <VStack p="4">
      <Box position="relative" w="full" textAlign="center">
        <Heading size="4xl">Pojo Generator</Heading>
        <Box position="absolute" top="0" right="0">
          <ColorModeButton />
        </Box>
      </Box>
      <Box>
        <Button variant="surface" colorPalette="teal">
          Submit
        </Button>
      </Box>
    </VStack>
  );
};

export default HomePage;
